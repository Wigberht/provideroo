<script type="text/x-template" id="tariff-row-admin-template">

    <div class="row zero-margin center-align">
        <template v-if="edit">
            <div class="col s2">
                <input type="text" v-model="d_title">
            </div>
            <div class="col s3">
                <textarea v-model="d_description"></textarea>
            </div>
            <div class="col s1">
                <input type="number" v-model="d_days">
            </div>
            <div class="col s1">
                <input type="number" v-model="d_cost">
            </div>
            <div class="col s1">
                <input type="text" v-model="d_currency">
            </div>
        </template>

        <template v-else>
            <div @mouseover="showTruncated"
                 @mouseleave="hideTruncated"
                 v-bind:class="{truncate:isTruncated}"
                 class="col s2">
                {{d_title}}
            </div>
            <div @mouseover="showTruncated"
                 @mouseleave="hideTruncated"
                 v-bind:class="{truncate:isTruncated}"
                 class="col s3">
                {{d_description}}
            </div>
            <div class="col s1">{{d_days}}</div>
            <div class="col s1">{{d_cost}}</div>
            <div class="col s1">{{d_currency}}</div>
        </template>

        <div class="col s2">{{subscribers_amount}}</div>
        <div class="col s2">
            <a v-if="!edit"
               @click="startEdit"
               class="waves-effect waves-light btn">
                {{buttonText}}
            </a>
            <a v-if="edit"
               @click="finishEdit"
               class="waves-effect waves-light btn">
                {{buttonText}}
            </a>
        </div>
    </div>

</script>

<script>
    Vue.component('tariff-row-admin', {
        props: [
            'id', 'title', 'description',
            'number_of_days', 'cost', 'currency',
            'edit_text', 'save_text'
        ],
        data: function () {
            return {
                defaultValue: {
                    d_title: this.title,
                    d_description: this.description,
                    d_days: this.number_of_days,
                    d_cost: this.cost,
                    d_currency: this.currency,
                },

                d_title: this.title,
                d_description: this.description,
                d_days: this.number_of_days,
                d_cost: this.cost,
                d_currency: this.currency,

                isTruncated: true,

                edit: false,
                subscribers_amount: 0
            }
        },
        template: "#tariff-row-admin-template",
        methods: {
            startEdit() {
                this.edit = true;
            },
            finishEdit() {
                axios.post("/rest/tariff/update", {
                    'id': this.id,
                    'title': this.d_title,
                    'description': this.d_description,
                    'numberOfDays': this.d_days,
                    'cost': this.d_cost,
                    'currencyShortname': this.d_currency,
                }).then((response) => {
                    console.log(response);
                    Materialize.toast("SUCCESS", 1500)
                }).catch((error) => {
                    Materialize.toast(this.tariff_update_fail, 1500);
                    console.log(error);
                });
                this.edit = false;
            },
            showTruncated() {
                this.isTruncated = false;
            },
            hideTruncated() {
                this.isTruncated = true;
            },
        },

        beforeMount() {

        },
        mounted: function () {
        },

        computed: {
            buttonText() {
                return this.edit ? this.save_text : this.edit_text;
            }
        }
    })
</script>