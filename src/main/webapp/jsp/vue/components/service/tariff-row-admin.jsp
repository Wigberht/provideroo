<script type="text/x-template" id="tariff-row-admin-template">
    <div class="tariff-row-card teal lighten-5">
        <div class="row zero-margin center-align">
            <template v-if="d_tariff.serviceTitle">
                <div class="col s2">
                    {{d_tariff.serviceTitle}}
                </div>
            </template>
            <template v-if="edit">
                <div class="col s2">
                    <input type="text" v-model="d_tariff.title">
                </div>
                <div class="col "
                     v-bind:class="{'s2' : d_tariff.serviceTitle, 's3' : !d_tariff.serviceTitle}">

                    <textarea v-model="d_tariff.description"></textarea>
                </div>
                <div class="col s1">
                    <input type="number" v-model="d_tariff.numberOfDays">
                </div>
                <div class="col"
                     v-bind:class="{'s1' : d_tariff.serviceTitle, 's2' : !d_tariff.serviceTitle}">

                    <input type="number" v-model="d_tariff.cost">
                    {{d_tariff.currencyShortname}}
                </div>
            </template>

            <template v-else>
                <div @mouseover="showTruncated"
                     @mouseleave="hideTruncated"
                     v-bind:class="{truncate:isTruncated}"
                     class="col s2">
                    {{d_tariff.title}}
                </div>
                <div @mouseover="showTruncated"
                     @mouseleave="hideTruncated"
                     class="col "
                     v-bind:class="{truncate:isTruncated,'s2' : d_tariff.serviceTitle, 's3' : !d_tariff.serviceTitle}">
                    {{d_tariff.description}}
                </div>
                <div class="col s1">{{d_tariff.numberOfDays}}</div>
                <div class="col"
                     v-bind:class="{'s1' : d_tariff.serviceTitle, 's2' : !d_tariff.serviceTitle}">
                    {{d_tariff.cost}} {{d_tariff.currencyShortname}}
                </div>
            </template>

            <div class="col s1">{{d_tariff.subscriberAmount}}</div>
            <div class="col s1">
                <a v-if="!edit"
                   @click="startEdit"
                   class="waves-effect waves-light btn btn-small">
                    <i class="material-icons">mode_edit</i>
                </a>
                <a v-if="edit"
                   @click="finishEdit"
                   class="waves-effect waves-light btn btn-small">
                    <i class="material-icons">save</i>
                </a>
            </div>
            <div class="col s1">
                <a @click="deleteTariff"
                   class="waves-effect waves-light btn btn-small red darken-5 tooltipped"
                   data-position="top" data-delay="200" data-tooltip="Delete">
                    <i class="material-icons">delete</i>
                </a>
            </div>
        </div>
    </div>
</script>

<script>
    Vue.component('tariff-row-admin', {
        props: [
            'tariff',
            'id', 'title', 'description', 'number_of_days', 'cost', 'currency', 'service',
            'subscribers'
        ],
        data: function () {
            return {
                d_tariff: "",

                isTruncated: true,
                edit: false,
            }
        },
        template: "#tariff-row-admin-template",
        methods: {
            startEdit() {
                this.edit = true;
            },
            finishEdit() {
                this.$emit("update", this.d_tariff);
                this.edit = false;
            },

            deleteTariff() {
                this.$emit("delete", this.d_tariff.id)
            },

            showTruncated() {
                this.isTruncated = false;
            },
            hideTruncated() {
                this.isTruncated = true;
            },

            isString(value) {
                return typeof value === 'string' || value instanceof String;
            }
        },

        beforeMount() {

        },
        mounted: function () {

            const tariff = this.tariff;
            this.d_tariff = this.isString(tariff) ? JSON.parse(tariff) : tariff;
        },

        computed: {
            buttonText() {
                return this.edit ? strings['save'] : strings['edit'];
            }
        },
        watch: {}
    });
</script>