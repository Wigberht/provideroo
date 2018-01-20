<script type="text/x-template" id="sort-dropdown-template">
    <%--<div class="col s12">--%>
        <select @change="changeSort($event)"
                id="sort-select">
            <option value="" disabled selected>{{text_sort}}</option>
            <option :value=url_name>{{text_by_name}}</option>
            <option :value=url_price>{{text_by_price}}</option>
        </select>
        <%--<label for="sort-select">{{text_sort}}</label>--%>
    <%--</div>--%>
</script>

<script>
    Vue.component('sort-dropdown', {
        props: ['text_sort', 'text_by_name', 'text_by_price', 'base_url'],
        template: "#sort-dropdown-template",
        data() {
            return {
                url_name: this.base_url + "?sort=name",
                url_price: this.base_url + "?sort=price",
                sort_type: ""
            }
        },
        methods: {
            changeSort(event) {
                console.log(event);
                console.log(event.target);
                console.log(event.target.value);

                window.location.href = event.target.value;
            }
        },

        mounted: function () {

        },

        watch: {
            sort_type(){
                console.log("hello")
            }
        },
        computed: {
            buttonText() {

            }
        }
    })
</script>
